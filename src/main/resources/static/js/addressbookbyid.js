// addressbookbyid.js

document.addEventListener("DOMContentLoaded", () => {
  console.log("AddressBook by ID page ready.");

  //Fade in effect
  document.body.style.opacity = 0;
  document.body.style.transition = "opacity 1.5s ease";
  requestAnimationFrame(() => (document.body.style.opacity = 1));

  //Dynamic header color pulse
  const header = document.querySelector("h2");
  if (header) {
    let hue = 180;
    setInterval(() => {
      hue = (hue + 1) % 360;
      header.style.color = `hsl(${hue}, 60%, 35%)`;
    }, 100);
  }

  //Style + form animation
  const forms = document.querySelectorAll("form");
  forms.forEach(form => {
    form.style.border = "2px solid #1abc9c";
    form.style.borderRadius = "10px";
    form.style.padding = "15px";
    form.style.boxShadow = "0 0 8px rgba(26, 188, 156, 0.2)";
    form.style.transition = "all 0.3s ease";

    form.addEventListener("mouseenter", () => {
      form.style.boxShadow = "0 0 16px rgba(26, 188, 156, 0.6)";
      form.style.transform = "scale(1.02)";
    });
    form.addEventListener("mouseleave", () => {
      form.style.boxShadow = "0 0 8px rgba(26, 188, 156, 0.2)";
      form.style.transform = "scale(1)";
    });
  });

  //highlight inputs on focus
  const inputs = document.querySelectorAll("input[type='text'], input[type='number']");
  inputs.forEach(input => {
    input.style.border = "1px solid #ccc";
    input.style.borderRadius = "6px";
    input.style.padding = "5px";
    input.style.transition = "border-color 0.3s ease, box-shadow 0.3s ease";

    input.addEventListener("focus", () => {
      input.style.borderColor = "#1abc9c";
      input.style.boxShadow = "0 0 6px rgba(26, 188, 156, 0.5)";
    });
    input.addEventListener("blur", () => {
      input.style.borderColor = "#ccc";
      input.style.boxShadow = "none";
    });
  });

  //Animate list of buddies
  const buddyItems = document.querySelectorAll("ul li");
  buddyItems.forEach((li, i) => {
    li.style.opacity = 0;
    li.style.transform = "translateY(10px)";
    li.style.transition = "all 0.5s ease";
    setTimeout(() => {
      li.style.opacity = 1;
      li.style.transform = "translateY(0)";
    }, 200 * i);
  });

  //background pulse
  let bgHue = 180;
  setInterval(() => {
    bgHue = (bgHue + 1) % 360;
    document.body.style.backgroundColor = `hsl(${bgHue}, 80%, 97%)`;
  }, 80);
});
