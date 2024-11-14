import React from "react";
import Image1 from "../Images/img1.jpg";
import "./HomePage.css";

function HomePage() {
  return (
    <div className="home">
      <section className="section1">
        <img src={Image1} alt="Iamge" className="img1"/>
      </section>
    </div>
  );
}

export default HomePage;
