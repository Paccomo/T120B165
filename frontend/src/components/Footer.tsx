import React from 'react';
import styled from 'styled-components';

const FooterContainer = styled.footer`
  background-color: #072E33;
  color: white;
  padding: 20px;
  text-align: center;
  margin: 10px 0 0 0;
`;

const Footer = () => {
  return (
    <FooterContainer>
      <div>
        <p style={{fontFamily: 'Rubik Broken Fax'}}>Do not contact us</p>
      </div>
      <div>
        <p style={{fontFamily: 'Rubik Broken Fax'}}>&copy; 2023 SRS. All rights reserved.</p>
      </div>
    </FooterContainer>
  );
};

export default Footer;