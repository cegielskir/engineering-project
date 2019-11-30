import styled from 'styled-components';

export const ResultItem = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
    border: 1px solid #326581;
    padding: 10px;
    margin: 6px 0;

    & > p {
        margin-top: 12px; 
    }
`;

export const Percentage = styled.p`
    font-size: 32px;
    color: #326581;
    margin: 20px 0;
`;

export const CheckResult = styled.div`
    display: flex;
    justify-content: center;
    align-items: center
`;

export const CenterWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center
    height: 100%;
`;

export const SpinnerWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    min-height: 560px;
`;

export const Button = styled.button`
    margin: 0 10px;
    color: #fff;
    background-color: #326581;

    &:hover {
        color: #326581;
        border: 1px solid #326581;
        background-color: #fff;
    }
`;

export const Pagination = styled.div`
    display: flex;
    justify-content: flex-end;
    width: 100%;
    margin: 20px 0;
`;

export const PageNr = styled.button`
    color: #fff;
    background-color: #326581;
    border-radius: 100%;
    height: 50px;
    width: 50px;
    margin: 6px
    min-width: 0;

    &:hover {
        color: #326581;
        border: 1px solid #326581;
        background-color: #fff;
    }
`;

export const CloseConnection = styled.button`

`;

export const ButtonWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`;


export const InputWrapper = styled.div`
    position: relative;
`;

export const Input = styled.input`
    border-radius: 20px;
    width: 264px;
    margin: 12px 0;
    border: 1px solid #326581;
    padding: 5px 10px;
`;

export const SearchButton = styled.button`
    position: absolute;
    top: 12px;
    right: 0;
    height: 30px;
    width: 30px;
    border-radius: 50%;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0;
    border: 1px solid #326581;
`;

export const SearchIcon = styled.img`
    width: 12px;
    height: 12px;
`;

export const Article = styled.div`
    margin: 30px;
`;
