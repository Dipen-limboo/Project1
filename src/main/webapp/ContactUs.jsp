<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us </title>
    <link rel="stylesheet" type="text/css" href="./CSS/styles.css">
</head>
<body>
    <jsp:include page="./Header/userHeader.jsp"></jsp:include>
    <main class= "contact_frame">
        <div class="left_contact">
            <h2>Contact Information</h2> 
            <p>Email: <span><a href="mailto:dipenlimboo.com">dipenlimboo564@gmail.com</a></span></p>
            <p>Phone: <span>+977 (980) 851-0564</span></p>
            <p>Mailing Address: <span>Mulpani pipalbot, Kathmandu (44600), Nepal</span></p>
        </div>

        <div class="main_contact" style="color: #333">
            <h2 style= "margin-bottom: 1rem; margin-top: 10px; color: #333">Contact Form</h2>
            <form action="contactFormProcessor.jsp" method="post">
                <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" style="width: 400px; border: 1px solid green; height: 20px" required><br>
				</div>
                <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" style="width: 400px; border: 1px solid green; height: 20px" required><br>
				</div>
				<div>
                <label for="message" style="padding-top: 15px">Message:</label>
                <textarea id="message" name="message" rows="5" style="margin-left:6px;width:400px;border:1px solid green" required></textarea><br>
				</div>
                <button type="submit" style="margin-left: 0.5rem;">Submit</button>
            </form>
        </div>
    </main>

<jsp:include page="./Footer/footer.jsp"></jsp:include>
</body>
</html>
