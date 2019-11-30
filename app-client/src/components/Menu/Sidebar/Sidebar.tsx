import React from 'react';
import * as P from './parts';

interface SidebarProps {
    show: boolean;
}

const Sidebar: React.FC<SidebarProps> = ({ show }) => {
  return (
    <P.Sidebar isOpen={show}>
        <P.SidebarList>
            <P.SidebarLink to="/">Home</P.SidebarLink>
            <P.SidebarLink to="/comparison">Compare two articles</P.SidebarLink>
            <P.SidebarLink to="/add-file">Add excel files</P.SidebarLink>
            <P.SidebarLink to="/add-article">Add single article</P.SidebarLink>
            <P.SidebarLink to="/find-article">Find article in database</P.SidebarLink>
        </P.SidebarList>
    </P.Sidebar>
  );
};

export default Sidebar;
