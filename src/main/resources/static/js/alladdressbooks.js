// alladdressbooks.js

document.addEventListener("DOMContentLoaded", () => {
  console.log("All AddressBooks page ready.");

  //Fade in effect
  document.body.style.opacity = 0;
  document.body.style.transition = "opacity 1.5s ease";
  requestAnimationFrame(() => (document.body.style.opacity = 1));

  //Dynamic heading animation
  const headings = document.querySelectorAll("h2");
  headings.forEach((h2, i) => {
    h2.style.opacity = 0;
    h2.style.transform = "translateY(10px)";
    h2.style.transition = "all 0.6s ease";
    h2.style.fontFamily = "Segoe UI, sans-serif";
    h2.style.color = "#2c3e50";
    h2.style.textShadow = "1px 1px 3px rgba(0,0,0,0.1)";

    // Stagger appearance for each address book
    setTimeout(() => {
      h2.style.opacity = 1;
      h2.style.transform = "translateY(0)";
    }, 300 * i);
  });

  //Animate Buddy List
  const buddyLists = document.querySelectorAll("ul");
  buddyLists.forEach((ul, listIndex) => {
    const buddies = ul.querySelectorAll("li");
    buddies.forEach((li, i) => {
      li.style.opacity = 0;
      li.style.transform = "translateX(-15px)";
      li.style.transition = "all 0.5s ease";
      li.style.padding = "6px";
      li.style.margin = "2px 0";
      li.style.borderRadius = "6px";
      li.style.backgroundColor = "white";
      li.style.boxShadow = "0 0 6px rgba(26, 188, 156, 0.2)";
      li.style.fontFamily = "Segoe UI, sans-serif";

      setTimeout(() => {
        li.style.opacity = 1;
        li.style.transform = "translateX(0)";
      }, 150 * i + 300 * listIndex);

      li.addEventListener("mouseenter", () => {
        li.style.backgroundColor = "#1abc9c";
        li.style.color = "white";
        li.style.boxShadow = "0 0 12px rgba(26, 188, 156, 0.6)";
      });

      li.addEventListener("mouseleave", () => {
        li.style.backgroundColor = "white";
        li.style.color = "#2c3e50";
        li.style.boxShadow = "0 0 6px rgba(26, 188, 156, 0.2)";
      });
    });
  });

  //Page title dynamic add
  const mainHeader = document.createElement("h1");
  mainHeader.textContent = "All Address Books";
  mainHeader.style.textAlign = "center";
  mainHeader.style.marginTop = "30px";
  mainHeader.style.fontFamily = "Segoe UI, sans-serif";
  mainHeader.style.color = "#1abc9c";
  mainHeader.style.textShadow = "2px 2px 4px rgba(0,0,0,0.1)";
  document.body.prepend(mainHeader);

  //background hue shift
  let hue = 180;
  setInterval(() => {
    hue = (hue + 1) % 360;
    document.body.style.backgroundColor = `hsl(${hue}, 80%, 97%)`;
  }, 80);
});
