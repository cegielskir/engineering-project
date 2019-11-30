import styled from 'styled-components';
import { media } from './media';

export const Container = styled.div`
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto

    ${media.greaterThan('sm')} {
        max-width: 540px
    }

    ${media.greaterThan('md')} {
        max-width: 720px
    }

    ${media.greaterThan('lg')} {
        max-width: 960px
    }

    ${media.greaterThan('lg')} {
        max-width: 1140px
    }
`;

export const Link = styled.a`
    text-decoration: none;
    color: #000;

    &:hover,
    &:active,
    &:focus {
        text-decoration: none;
    }
`;

export const Image = styled.img``;

export const Button = styled.div<{disabled: boolean}>`
    background-color: ${props => props.disabled ? 'grey' : '#326581'};
    color: #fff; 

    &:hover {
        color:  ${props => props.disabled ? '#fff' : '#326581'}; 
        background-color: ${props => props.disabled ? 'grey' : '#fff'}; 
    }
`;

export const Row = styled.div`
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px
`;

/*
export const 

.col,
.col-1,
.col-10,
.col-11,
.col-12,
.col-2,
.col-3,
.col-4,
.col-5,
.col-6,
.col-7,
.col-8,
.col-9,
.col-auto {
    position: relative;
    width: 100%;
    padding-right: 15px;
    padding-left: 15px
}

.col {
    -ms-flex-preferred-size: 0;
    flex-basis: 0;
    -ms-flex-positive: 1;
    flex-grow: 1;
    max-width: 100%
}

.col-auto {
    -ms-flex: 0 0 auto;
    flex: 0 0 auto;
    width: auto;
    max-width: 100%
}

.col-1 {
    -ms-flex: 0 0 8.333333%;
    flex: 0 0 8.333333%;
    max-width: 8.333333%
}

.col-2 {
    -ms-flex: 0 0 16.666667%;
    flex: 0 0 16.666667%;
    max-width: 16.666667%
}

.col-3 {
    -ms-flex: 0 0 25%;
    flex: 0 0 25%;
    max-width: 25%
}

.col-4 {
    -ms-flex: 0 0 33.333333%;
    flex: 0 0 33.333333%;
    max-width: 33.333333%
}

.col-5 {
    -ms-flex: 0 0 41.666667%;
    flex: 0 0 41.666667%;
    max-width: 41.666667%
}

.col-6 {
    -ms-flex: 0 0 50%;
    flex: 0 0 50%;
    max-width: 50%
}

.col-7 {
    -ms-flex: 0 0 58.333333%;
    flex: 0 0 58.333333%;
    max-width: 58.333333%
}

.col-8 {
    -ms-flex: 0 0 66.666667%;
    flex: 0 0 66.666667%;
    max-width: 66.666667%
}

.col-9 {
    -ms-flex: 0 0 75%;
    flex: 0 0 75%;
    max-width: 75%
}

.col-10 {
    -ms-flex: 0 0 83.333333%;
    flex: 0 0 83.333333%;
    max-width: 83.333333%
}

.col-11 {
    -ms-flex: 0 0 91.666667%;
    flex: 0 0 91.666667%;
    max-width: 91.666667%
}

.col-12 {
    -ms-flex: 0 0 100%;
    flex: 0 0 100%;
    max-width: 100%
}*/
