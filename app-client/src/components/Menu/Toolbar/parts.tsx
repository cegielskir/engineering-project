import styled from 'styled-components';

export const Toolbar = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100px;
  padding: 14px 44px;
  z-index: 100;
`;

export const ToolbarNavigation = styled.nav`
  display: flex;
  height: 100%;
  align-items: center;
  justify-content: space-between;
`;


export const ToolbarLogo = styled.div`
  margin-left: 1rem;
  display: flex;

  & > a {
    color: #fff;
    text-decoration: none;
    font-size: 1.5rem;
  }

  @media (min-width: 768px) {
    margin-left: 0;
  }
`;

export const ToolbarNavigationItems = styled.div`
    & > ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
        
        & > li {
            padding: 0 0.5rem;

            & > a {
                color: #fff;
                text - decoration: none;

                &:hover,
                &:active {
                    color: #e8a822;
                }
            }
        }
    }
`;
