import React from 'react';
import Toolbar from './Toolbar/Toolbar';
import Sidebar from './Sidebar/Sidebar';
import Backdrop from './Backdrop/Backdrop';

interface MenuProps {
  className?: string;
};

interface MenuState {
  isOpen: boolean;
}

class Menu extends React.Component<MenuProps, MenuState> {
  state = {
    isOpen: false
  }

  drawerToggleClickHandler = () => {
    this.setState((prevState) => ({
      isOpen: !prevState.isOpen,
    }));
  };

  backdropClickHandler = () => {
    this.setState({
      isOpen: false
    });
  };

  render() {
    let backdrop;

    if (this.state.isOpen) {
      backdrop = <Backdrop onClick={this.backdropClickHandler} />
    }

    return (
      <div>
        <Toolbar onToggleButtonClick={this.drawerToggleClickHandler} />
        <Sidebar show={this.state.isOpen} />
        {backdrop}
      </div>
    );
  }
}

export default Menu;