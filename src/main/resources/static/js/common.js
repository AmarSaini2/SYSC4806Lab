//themed home button
const backBtn = document.getElementById("homeBtn");

if (backBtn) {
  //match theme with button styling
  backBtn.style.position = "fixed";
  backBtn.style.bottom = "25px";
  backBtn.style.right = "25px";
  backBtn.style.padding = "12px 20px";
  backBtn.style.fontSize = "1rem";
  backBtn.style.fontFamily = "Segoe UI, sans-serif";
  backBtn.style.color = "#1abc9c";
  backBtn.style.border = "2px solid #1abc9c";
  backBtn.style.background = "white";
  backBtn.style.borderRadius = "12px";
  backBtn.style.cursor = "pointer";
  backBtn.style.boxShadow = "0 0 10px rgba(26, 188, 156, 0.3)";
  backBtn.style.transition = "all 0.3s ease";
  backBtn.style.zIndex = "999";

  // Hover glow effect
  backBtn.addEventListener("mouseenter", () => {
    backBtn.style.background = "#1abc9c";
    backBtn.style.color = "white";
    backBtn.style.boxShadow = "0 0 18px rgba(26, 188, 156, 0.7)";
    backBtn.style.transform = "scale(1.05)";
  });
  backBtn.addEventListener("mouseleave", () => {
    backBtn.style.background = "white";
    backBtn.style.color = "#1abc9c";
    backBtn.style.boxShadow = "0 0 10px rgba(26, 188, 156, 0.3)";
    backBtn.style.transform = "scale(1)";
  });

  // Click → go home
  backBtn.addEventListener("click", () => {
    window.location.href = "/";
  });
}
