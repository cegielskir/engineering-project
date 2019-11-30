import React from 'react';
import * as P from './parts';

interface TopbarMessageProps {
    label: string;
    color: string;
    open: boolean;
}

interface TopbarMessageState {
    isOpen: boolean;
}

class TopbarMessage extends React.Component<TopbarMessageProps, TopbarMessageState> {
    state = {
        isOpen: true,
    }

    render() {
        const { open, color, label } = this.props;
        const { isOpen } = this.state;

        return(
            <P.Wrapper open={open && isOpen}>
                <P.Topbar color={color}>
                    <P.Label>{label}</P.Label>
                    <P.CloseButton onClick={() => this.setState({
                        isOpen: false,
                    })}>
                        X
                    </P.CloseButton>
                </P.Topbar>
            </P.Wrapper>
        );

    }

}

export default TopbarMessage;
