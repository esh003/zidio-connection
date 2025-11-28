🚀 Zidio Connection – Backend (Spring Boot)

A full-stack backend platform built using Spring Boot, designed to manage jobseekers, recruiters, job posts, applications, subscription plans, payments, invoices, notifications, dashboard analytics, and course management — all in one place.

This project follows production-grade backend architecture with proper modularization, DTOs, services, repositories, exception handling, security, Cloudinary uploads, and Mailtrap-based email notifications.

⭐ Features (Everything Implemented)
🔐 Authentication & Users

JWT Authentication

Login / Signup

Role-based access (STUDENT / RECRUITER / ADMIN)

👤 Jobseeker & Recruiter Profiles

Profile creation & update

Experience, education, resume upload (Cloudinary)

💼 Job Posts Module

Recruiter can create job posts

Students can view & apply

📄 Application Module

Apply to jobs

View application status

Recruiter sees list of applicants

📷 Cloudinary Integration

Upload profile images

Upload resumes

Upload job-related media

✉️ Notification System (Mailtrap)

Sends emails for:

Registration

Job application

Payment confirmation

Invoice emails

Supports attachments

🎓 Course Management

Admin can create courses

Filter by category

Activate / deactivate

📊 Dashboard Analytics

Total jobseekers

Total recruiters

Total job posts

Total applications

Active courses

💳 Subscription Plans

Create plans (MONTHLY / QUARTERLY / YEARLY etc.)

Activate/deactivate

Filter by duration

🧾 Payments

Create payment record

Auto-generate transaction ID

Payment status

Fetch by user / all payments

📄 Invoice Generation (iText PDF)

Generates PDF invoice for each payment

Saves under /Invoices folder

Can be emailed as attachment

🛠️ Tech Stack

Backend:

Java 17

Spring Boot 3

Spring Web, JPA, Security, Validation

MySQL

JWT (io.jsonwebtoken)

iTextPDF (for invoice generation)

Utilities:

Cloudinary for file uploads

Mailtrap SMTP for sending emails

Lombok

Maven