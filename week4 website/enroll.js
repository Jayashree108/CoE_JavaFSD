document.addEventListener("DOMContentLoaded", function () {
    var form = document.getElementById("enrollForm");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var phone = document.getElementById("phone").value;
        var email = document.getElementById("email").value;
        var address = document.getElementById("address").value;
        var dob = document.getElementById("dob").value;
        var course = document.getElementById("course").value;
        if (!validateForm(firstName, lastName, phone, email, address, dob)) {
            alert("Please fill out all fields correctly.");
            return;
        }
        var userDetails = {
            firstName: firstName,
            lastName: lastName,
            phone: phone,
            email: email,
            address: address,
            dob: dob,
            course: course
        };
        localStorage.setItem("enrolledUser", JSON.stringify(userDetails));
        alert("Enrollment Successful!");
        var coursePage = "";
        switch (course) {
            case "Web Development":
                coursePage = "wd.html";
                break;
            case "Data Science":
                coursePage = "ds.html";
                break;
            case "Machine Learning":
                coursePage = "ml.html";
                break;
            default:
                coursePage = "course-details.html";
        }
        window.location.href = coursePage;
    });
    function validateForm(firstName, lastName, phone, email, address, dob) {
        var nameRegex = /^[A-Za-z]+$/;
        var phoneRegex = /^[0-9]{10}$/;
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        var today = new Date().toISOString().split('T')[0];
        if (!nameRegex.test(firstName) || !nameRegex.test(lastName)) {
            alert("Names should contain only alphabets.");
            return false;
        }
        if (!phoneRegex.test(phone)) {
            alert("Phone number should be 10 digits.");
            return false;
        }
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address.");
            return false;
        }
        if (dob === today) {
            alert("Date of birth cannot be today's date.");
            return false;
        }
        if (firstName.trim() === "" || lastName.trim() === "" || address.trim() === "" || dob.trim() === "") {
            alert("All fields are required.");
            return false;
        }
        return true;
    }
});
