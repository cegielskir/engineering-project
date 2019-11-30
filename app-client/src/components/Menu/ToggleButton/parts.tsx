import styled from 'styled-components';

export const ToggleButton = styled.button`
    flex-direction: column;
    justify-content: space-around;
    float: right;
    height: 24px;
    width: 30px;
    background: transparent;
    border: none;
    cursor: pointer;
    padding: 0;
    box-sizing: border-box;
    display: flex;

    &:focus {
        outline: none;
    }
`;

export const ToggleButtonLine = styled.div`
    width: 30px;
    height: 2px;
    background: #326581;
`;
