import styled from 'styled-components';

const complete = '#f68e20';
const active = '#f68e20';
const mute = '#e6e6e6';

export const Steps = styled.ol`
    list-style: none;
    margin: 0;
    padding: 0;
    display: table;
    table-layout: fixed;
    width: 100%;
    color: darken(${mute}, 33%);
    height:4rem;

    > .step {
    position: relative;
    display: table-cell;
    text-align: center;
    font-size: 0.875rem;
    color:#6D6875;
  
        &:before {
            content: attr(data-step);
            display: block;
            margin: 0 auto;
            background: #ffffff;
            border:2px solid ${mute};
            color:${mute};
            width: 2rem;
            height: 2rem;
            text-align: center;
            margin-bottom: -4.2rem;
            line-height: 1.9rem;
            border-radius: 100%;
            position: relative;
            z-index: 1;
            font-weight:700;
            font-size:1rem;
        }

        &:after {
            content: '';
            position: absolute;
            display: block;
            background: ${mute};
            width: 100%;
            height: 0.125rem;
            top: 1rem;
            left: 50%;
        }

        &:last-child:after {
            display: none;
        }

        &.is-complete {
            color:#6D6875;
            
            &:before {
                content:'\2713';
                color: ${complete};
                background: #fef0e2;
                border:2px solid ${complete};
            }

            &:after {
                background: ${complete};
            }
        }

        &.is-active {
            font-size:1.5rem;

            &:before {
                color: #FFF;
                border:2px solid ${complete};
                background: ${active};
                margin-bottom: -4.9rem;
            }
        }
    }

    .steps {
        margin-bottom: 3em;
    }
`


