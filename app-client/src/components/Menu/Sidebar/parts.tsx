import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

interface SidebarProps {
    isOpen: boolean;
}

export const Sidebar: React.FC<SidebarProps> = styled.nav<{ isOpen: boolean }>`
    height: 100%;
    background: white;
    box-shadow: 1px 0px 7px rgba(0, 0, 0, 0.8);
    position: fixed;
    top: 0;
    left: 0;
    width: 70%;
    max-width: 400px;
    z-index: 200;
    transform: ${props => props.isOpen ? 'translateX(0);' : 'translateX(-100%)'};
    transition: transform 0.3s ease-out;
`;

export const SidebarList = styled.ul`
    margin-top: 40px;
    height: 100%;
    list-style: none;
    display: flex;
    flex-direction: column;
`;

export const SidebarLink = styled(Link)`
    margin: 0.5rem 0;

        color: #326581;
        text-decoration: none;
        font-size: 1.2rem;

        &:hover,
        &:active {
            color: #87bad6;
        }
    
`;
