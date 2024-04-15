import React from 'react';
import './aboutUs.css';
import frontview from '../images/frontview_church.jpg'

function AboutUs() {
  return (
    <main className="aboutUs_main">
      <div className='aboutUs_body'>
        <img src={frontview} className='aboutUs_img'></img>
        <section className='aboutUs_sections'>
          <h3 className='aboutUs_titles'>사명</h3>
          <p>땅끝까지 이르러 예수 그리스도의 증인이 되는 교회</p>
          <p className="aboutUs_quote_and_source">
              <blockquote className='aboutUs_quote_border'>
              <b>
                "오직 성령이 너희에게 임하시면 너희가 권능을 받고 예루살렘과
                온 유대와 사마리아와 땅 끝까지 이르러 내 증인이 되리라 하시니라" {"\n"}
                  <p><i>- 사도행전1장 8절</i></p>
              </b>
              </blockquote>
          </p>
          <p>
              주님이 주신 마지막 이땅의 사명을 완수하고 이루기까지 가정과 지역과 온 열방을 향하여 전진하며 그리스도께서 주신
              빛과 소금의 귀중한 사명을 감당하는 복된 교회로 서기를 원합니다. 삶이 사명이 되어 복음의 전신갑주를 취하고 주님 
              부르시는 그 자리까지 주님 부르시는 그날까지 충성과 헌신과 사랑으로 주님의 가신 길을 걷는 작은 예수의 사명을 감당할 것입니다.
          </p>
        </section>

        <section className='aboutUs_sections'>
          <h3 className='aboutUs_titles'>사역</h3>
          <p>하나님의 말씀과 기도와 찬양이 넘치는 교회 성도의 교재가 하나님의 사랑으로 하나되는 교회 복음을 위해 힘쓰며 사랑의 수고를 아끼지 않는 교회</p>
        </section>

        <section className='aboutUs_sections'>
          <h3 className='aboutUs_titles'>공동체 사역</h3>
          <p>
              이 세상에서 말씀의 증인으로 예수 그리스도의 십자가의 그 사랑을
              전하는 교회로 신실한 예배자들이 말씀으로 훈련되어 성령의 능력으로
              교회와 지역을 섬기는 자들이 넘치는 교회가 되길 기도합니다.
          </p>
        </section>
      </div>
    </main>
  );
}

export default AboutUs;
