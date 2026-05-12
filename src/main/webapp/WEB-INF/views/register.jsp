<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký - SocialLite</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #f0f2f5; height: 100vh; display: flex; align-items: center; }
        .reg-card { width: 100%; max-width: 430px; padding: 20px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); background: #fff; }
    </style>
</head>
<body>
<div class="container d-flex justify-content-center">
    <div class="reg-card">
        <h3 class="fw-bold mb-1">Đăng ký</h3>
        <p class="text-muted small">Nhanh chóng và dễ dàng.</p>
        <hr>
        <form action="<c:url value='/register'/>" method="post">
            <div class="mb-3">
                <input type="text" name="user" class="form-control" placeholder="Tên đăng nhập" required>
            </div>
            <div class="mb-3">
                <input type="password" name="pass" class="form-control" placeholder="Mật khẩu mới" required>
            </div>
            <div class="mb-3">
                <input type="password" name="repass" class="form-control" placeholder="Xác nhận mật khẩu" required>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger p-2 small">${error}</div>
            </c:if>
            <button type="submit" class="btn btn-success w-100 fw-bold">Đăng ký</button>
        </form>
        <div class="mt-3 text-center">
            <a href="<c:url value='/login'/>" class="text-decoration-none">Bạn đã có tài khoản?</a>
        </div>
    </div>
</div>
</body>
</html>