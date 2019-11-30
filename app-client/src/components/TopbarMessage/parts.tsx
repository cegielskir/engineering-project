import styled from 'styled-components';

export const Wrapper = styled.div<{ open: boolean }>`
    display: ${props => props.open ? 'flex' : 'none' };
    top: 120px;
    position: fixed;
    width: 100%;
`;

export const Topbar = styled.div<{ color: string }>`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 16px
    background-color: ${props => props.color};
    width: 100%;
`;

export const Label = styled.p`
    font-size: 18px;
    color: #fff;
    padding: 0;
    margin: 0;
`;

export const CloseButton = styled.button`
    background-color: transparent;
    color: fff;
`;
