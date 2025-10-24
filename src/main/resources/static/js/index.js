//index js file

//Run when page is loaded
document.addEventListener("DOMContentLoaded", () => {
    console.log("AddressBook landing page loaded.");

    //Page fade-in
    document.body.style.opacity = 0;
    document.body.style.transition = "opacity 1.5s ease";
    requestAnimationFrame(() => (document.body.style.opacity = 1));

    //Dynamic welcome heading
    const heading = document.createElement("h1");
    heading.textContent = "AddressBook Repo";
    heading.style.textAlign = "center";
    heading.style.marginTop = "40px";
    heading.style.color = "#2c3e50";
    heading.style.fontFamily = "Arial, sans-serif";
    heading.style.textShadow = "2px 2px 4px rgba(0,0,0,0.1)";
    document.body.prepend(heading);

    //Grab all links on page
    const links = document.querySelectorAll("a");

    //Style links
    links.forEach(link => {
        link.style.display = "block";
        link.style.margin = "20px auto";
        link.style.textAlign = "center";
        link.style.textDecoration = "none";
        link.style.fontFamily = "Segoe UI, sans-serif";
        link.style.color = "#1abc9c";
        link.style.transition = "all 0.3s ease";
        link.style.width = "250px";
        link.style.padding = "10px";
        link.style.border = "2px solid #1abc9c";
        link.style.borderRadius = "12px";
        link.style.boxShadow = "0 0 8px rgba(26, 188, 156, 0.3)";
        link.style.backgroundColor = "white";
    });

    //Add hover effect and click logging
    links.forEach(link => {
        link.addEventListener("mouseenter", () =>{
            link.style.backgroundColor = "#1abc9c";
            link.style.color = "white";
            link.style.boxShadow = "0 0  16px rgba(26, 188, 156, 0.8)";
            link.style.transform = "scale(1.05)";
        });

        link.addEventListener("mouseleave", () => {
            link.style.backgroundColor = "white";
            link.style.color = "#1abc9c";
            link.style.boxShadow = "0 0 8px rgba(26, 188, 156, 0.3)";
            link.style.transform = "scale(1)";
        });

        link.addEventListener("click", event => {
            console.log('Navigating to ${link.getAttribute("href")}');
        });
    });

    //background color pulse effect
    let hue = 180;
    setInterval(() => {
    hue = (hue + 1) % 360
    document.body.style.backgroundColor = 'hsl(${hue}, 80%, 95%)';
    }, 80);
})