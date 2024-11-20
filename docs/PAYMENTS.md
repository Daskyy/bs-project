// For immediate payment (like a server-side charge)
Payment payment = new Payment("pm_card_visa", 1000); // 10.00 EUR
boolean success = paymentService.processPayment(payment);

// For client-side payment flow
Payment payment = new Payment(null, 1000);
boolean created = paymentService.createOpenPayment(payment);
// Then pass the PaymentIntent to your client