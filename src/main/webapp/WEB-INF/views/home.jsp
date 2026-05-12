<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Social Media Lite - News Feed</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        .navbar { background-color: #ffffff; box-shadow: 0 2px 4px rgba(0,0,0,.1); }
        .post-card { border-radius: 8px; border: none; transition: 0.3s; }
        .post-card:hover { box-shadow: 0 4px 12px rgba(0,0,0,.15) !important; }
        .btn-post { border-radius: 20px; padding: 6px 25px; font-weight: bold; }
        .avatar-circle { width: 40px; height: 40px; background-color: #0d6efd; color: white; display: flex; align-items: center; justify-content: center; border-radius: 50%; font-weight: bold; }
        /* Tùy chỉnh thêm cho thanh search trên nav */
        .search-nav { max-width: 400px; width: 100%; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light sticky-top mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold text-primary fs-3" href="home">SocialLite</a>

        <form action="search" method="get" class="d-flex search-nav mx-lg-auto my-2 my-lg-0">
            <input name="txt" value="${searchValue}" class="form-control me-2 bg-light border-0" type="search" placeholder="Tìm kiếm bài viết, người đăng..." aria-label="Search">
            <button class="btn btn-outline-primary" type="submit">Tìm</button>
        </form>

        <div class="d-flex align-items-center">
            <c:choose>
                <c:when test="${sessionScope.userAccount != null}">
                    <span class="me-3 d-none d-md-inline text-secondary">Chào, <strong>${sessionScope.userAccount.username}</strong></span>
                    <a href="logout" class="btn btn-outline-danger btn-sm">Đăng xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/login'/>" class="btn btn-primary btn-sm px-4">Đăng nhập</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-7 col-md-9">

            <c:if test="${sessionScope.userAccount != null}">
                <div class="card post-card mb-4 shadow-sm border-top border-primary border-4">
                    <div class="card-body">
                        <h6 class="card-title fw-bold mb-3 text-secondary">Tạo bài viết mới</h6>
                        <form action="createPost" method="post">
                            <div class="mb-2">
                                <input type="text" name="title" class="form-control" placeholder="Tiêu đề bài viết..." required>
                            </div>
                            <div class="mb-3">
                                <textarea name="body" class="form-control" rows="3" placeholder="${sessionScope.userAccount.username} ơi, bạn đang nghĩ gì thế?" required></textarea>
                            </div>
                            <input type="hidden" name="status" value="active">
                            <div class="text-end">
                                <button type="submit" class="btn btn-primary btn-post">Đăng bài</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>

            <h5 class="mb-3 fw-bold text-dark px-1">
                <c:choose>
                    <c:when test="${not empty searchValue}">
                        Kết quả tìm kiếm cho: <span class="text-primary">"${searchValue}"</span>
                    </c:when>
                    <c:when test="${sessionScope.userAccount != null}">
                        Bảng tin của bạn (News Feed)
                    </c:when>
                    <c:otherwise>
                        Bài viết mới nhất
                    </c:otherwise>
                </c:choose>
            </h5>

            <c:forEach items="${listP}" var="p">
                <div class="card post-card mb-3 shadow-sm">
                    <div class="card-body">
                        <div class="d-flex align-items-center mb-3">
                            <div class="avatar-circle me-2">
                                    ${p.authorName.substring(0,1).toUpperCase()}
                            </div>
                            <div>
                                <h6 class="mb-0 fw-bold">${p.authorName}</h6>
                                <small class="text-muted" style="font-size: 0.7rem;">${p.createdAt}</small>
                            </div>
                        </div>

                        <h5 class="card-title text-dark fw-bold">${p.title}</h5>
                        <p class="card-text text-secondary" style="white-space: pre-wrap;">${p.body}</p>

                        <div class="mt-3 pt-2 border-top d-flex justify-content-between align-items-center">
                            <span class="badge bg-light text-primary border">${p.status}</span>
                            <div class="btn-group">
                                <button class="btn btn-sm btn-light text-muted">Thích</button>
                                <button class="btn btn-sm btn-light text-muted">Bình luận</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty listP}">
                <div class="text-center mt-5 p-5 bg-white rounded shadow-sm">
                    <img src="https://cdn-icons-png.flaticon.com/512/7486/7486744.png" width="100" class="mb-3 opacity-50" alt="Empty">
                    <p class="text-muted">Không tìm thấy bài viết nào phù hợp.</p>
                    <a href="home" class="btn btn-link">Quay lại trang chủ</a>
                </div>
            </c:if>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>