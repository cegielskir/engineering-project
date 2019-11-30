import styled from 'styled-components';
import { Container } from 'assets/global-styles';
import background from 'assets/img/background.png';

export const FormWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 40px;
`;

export const PageContainer = styled.div`
    ${Container}
`;

export const TextArea = styled.textarea`
    width: 640px;
    margin: 20px;
`;

export const WavesSpace = styled.div`
    height: auto;
    min-height: 460px;
    background-repeat: no-repeat;
    background-size: 100% auto;
    background-image: url(${background});
    position: relative;
    padding-top: 10%; 

    & h3{
        color: #fff;
    }
    & h3 > i{
        color: rgba(114, 165, 193, 0.9);
    }
`;

export const WavesSpaceContent = styled.div`
    margin-left: 100px;
    width: 60%;
`;

export const RadioWrapper = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    margin: 16px 0;
`;

export const Radio = styled.div<{ isSelected: boolean }>`
    margin: 0 10px;
    padding: 8px;
    color: ${props => props.isSelected ? '#fff' : '#326581'};
    background-color: ${props => props.isSelected ?  '#326581' : '#fff'};
    cursor: pointer
`;

export const RadioHeader = styled.h6`
    margin: 0;
`;

export const Label = styled.div`

`;

export const AdvancedSettings = styled.div<{ isOpen: boolean }>`
    display: ${props => props.isOpen ? 'flex' : 'none'};
    height: ${props => props.isOpen ? '300px' : '0'};
    justify-content: center;
    align-items: center;
    flex-direction: column;
    overflow: hidden;
    margin-bottom: 40px;
	transition: height 0.3s linear 0s;
`;


export const ButtonWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    width: 400px;
`;

export const Input = styled.input`
    border-radius: 20px;
    width: 84px;
    margin: 12px 0;
    border: 1px solid #326581;
    padding: 2px 10px;
`;
