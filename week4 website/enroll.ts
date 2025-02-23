document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("enrollForm") as HTMLFormElement;

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const firstName = (document.getElementById("firstName") as HTMLInputElement).value;
        const lastName = (document.getElementById("lastName") as HTMLInputElement).value;
        const phone = (document.getElementById("phone") as HTMLInputElement).value;
        const email = (document.getElementById("email") as HTMLInputElement).value;
        const address = (document.getElementById("address") as HTMLTextAreaElement).value;
        const dob = (document.getElementById("dob") as HTMLInputElement).value;
        const course = (document.getElementById("course") as HTMLSelectElement).value;

        if (!validateForm(firstName, lastName, phone, email, address, dob)) {
            alert("Please fill out all fields correctly.");
            return;
        }

        const userDetails = {
            firstName,
            lastName,
            phone,
            email,
            address,
            dob,
            course
        };

        localStorage.setItem("enrolledUser", JSON.stringify(userDetails));
        alert("Enrollment Successful!");

        let coursePage = "";
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

    function validateForm(firstName: string, lastName: string, phone: string, email: string, address: string, dob: string): boolean {
        const nameRegex = /^[A-Za-z]+$/;
        const phoneRegex = /^[0-9]{10}$/;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const today = new Date().toISOString().split('T')[0];

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
