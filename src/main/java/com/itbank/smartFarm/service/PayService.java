package com.itbank.smartFarm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PayService {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Value("${portone.api_key}")
        private String apiKey;

        @Value("${portone.api_secret}")
        private String apiSecret;

        public OrderVO checkout(Long memberId) {
            CartVO cart = findCartByMemberId(memberId);
            OrderVO order = new OrderVO();
            order.setMemberId(memberId);
            order.setStatus("PENDING");
            order.setTotalPrice(cart.getTotalPrice());
            createOrder(order);

            for (CartItemVO cartItem : cart.getCartItems()) {
                OrderItemVO orderItem = new OrderItemVO();
                orderItem.setOrderId(order.getId());
                orderItem.setItemId(cartItem.getItemId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getPrice());
                orderItem.setItemName(cartItem.getItemName());
                createOrderItem(orderItem);
            }

            return order;
        }

        public void updateOrderStatus(Long orderId, String status) {
            String sql = "UPDATE orders SET status = ? WHERE id = ?";
            jdbcTemplate.update(sql, status, orderId);
        }

        public MemberVO getMemberDetails(Long memberId) {
            String sql = "SELECT * FROM member WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{memberId}, (rs, rowNum) -> {
                MemberVO member = new MemberVO();
                member.setId(rs.getLong("id"));
                member.setUsername(rs.getString("username"));
                member.setAddress(rs.getString("address"));
                member.setPhoneNumber(rs.getString("phone_number"));
                member.setEmail(rs.getString("email"));
                return member;
            });
        }

        // 포트원 API 연동 메소드
        public String processPayment(OrderVO order) {
            try {
                String endpoint = "https://api.portone.io/v1/payments"; // 실제 엔드포인트로 변경 필요
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(endpoint))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(getPaymentPayload(order)))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    // 포트원 API 응답에서 결제 ID를 추출합니다.
                    return extractPaymentId(response.body());
                } else {
                    throw new RuntimeException("Payment processing failed");
                }
            } catch (Exception e) {
                throw new RuntimeException("Payment processing error", e);
            }
        }

        private String getPaymentPayload(OrderVO order) {
            // 결제 요청 payload를 JSON 형식으로 작성합니다.
            return String.format("{ \"amount\": %.2f, \"orderId\": \"%d\", \"apiKey\": \"%s\", \"apiSecret\": \"%s\" }",
                    order.getTotalPrice(), order.getId(), apiKey, apiSecret);
        }

        private String extractPaymentId(String responseBody) {
            // 포트원 API 응답에서 결제 ID를 추출합니다.
            // JSON 파싱 라이브러리를 사용하여 구현할 수 있습니다.
            // 예: ObjectMapper를 사용하여 JSON 응답을 파싱하고 결제 ID를 추출합니다.
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            return responseJson.path("response").path("paymentId").asText();
        }

        // DAO 메소드들


    }
