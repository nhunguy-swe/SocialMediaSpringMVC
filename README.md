# SocialLite - Spring MVC Social Media Project

**SocialLite** là một ứng dụng mạng xã hội thu nhỏ được xây dựng trên nền tảng Java Spring MVC.
---

## Tính năng chính
* **Xác thực:** Đăng ký, Đăng nhập, Đăng xuất với Session.
* **Bảng tin (News Feed):** Hiển thị danh sách bài viết mới nhất từ cộng đồng.
* **Đăng bài:** Cho phép người dùng đã đăng nhập chia sẻ trạng thái mới.
* **Tìm kiếm:** Tìm kiếm bài viết theo tiêu đề hoặc nội dung.
* **Giao diện:** Responsive sử dụng Bootstrap 5, giao diện thân thiện, hiện đại.

## Công nghệ sử dụng
* **Backend:** Java 17+, Spring MVC (Jakarta EE - Tomcat 10).
* **Frontend:** JSP, JSTL, Bootstrap 5.
* **Database:** SQL Server / MySQL (Kết nối qua JDBC).
* **Build Tool:** Maven.

## Cấu trúc dự án (Architecture)
Dự án được tổ chức theo mô hình MVC:
- `controller`: Xử lý điều hướng và logic nghiệp vụ.
- `dao`: Truy xuất dữ liệu (Data Access Object).
- `entity`: Các lớp thực thể (User, Post, Follow).
- `utils`: Tiện ích kết nối Database (DBUtils).
- `views`: Giao diện JSP nằm trong thư mục bảo mật `WEB-INF`.

## Hướng dẫn cài đặt
1. **Clone dự án:**
   ```bash
   git clone [https://github.com/YourUsername/SocialMediaSpringMVC.git](https://github.com/YourUsername/SocialMediaSpringMVC.git)

2. **Cấu hình Database:**
* Chạy file script SQL (nếu có) hoặc tạo bảng `users` và `posts`.
* Sửa thông tin tài khoản/mật khẩu DB trong `com.social.utils.DBUtils`.
  
3. **Chạy trên IntelliJ:**
* Mở dự án dưới dạng Maven Project.
* Cấu hình Local Tomcat 10+.
* Fix Artifacts (đảm bảo thư viện nằm trong `WEB-INF/lib`).
* Nhấn Run.

4. **Tài khoản kiểm thử**
* **Username**: `admin`
* **Password**: `123`
