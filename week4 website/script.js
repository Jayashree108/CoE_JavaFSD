

const track = document.querySelector(".carousel");
const items = document.querySelectorAll(".carousel-item");
const prevBtn = document.querySelector(".prev-btn");
const nextBtn = document.querySelector(".next-btn");
let index = 0;

function updateCarousel() {
    track.style.transform = `translateX(-${index * 100}%)`;
}

nextBtn.addEventListener("click", () => {
    index = (index + 1) % items.length;
    updateCarousel();
});

prevBtn.addEventListener("click", () => {
    index = (index - 1 + items.length) % items.length;
    updateCarousel();
});
