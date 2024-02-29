import React from 'react';

function MainNavBits() {
    return (
        <div style={mainContainerStyle}>
            <div style={containerGroupStyle}>
                <div style={containerStyle('#276622')}>
                    <a href="#"><b>Events</b></a>
                </div>
                <div style={containerStyle('#0082b4')}>
                    <a href="#"><b>Photos</b></a>
                </div>
                <div style={containerStyle('#bd479b')}>
                    <a href="#"><b>Past Sermons</b></a>
                </div>
                <div style={containerStyle('#e60002')}>
                    <a href="#"><b>About Us</b></a>
                </div>
            </div>
        </div>
    );
}

const mainContainerStyle = {
    border: '4px solid darkgreen',
    background: '#e6ffe6',
    padding: '15px',
    marginTop: '40px',
    width: '50%',
    margin: '20px auto',
    borderRadius:'10px',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.3)',
};

const containerGroupStyle = {
    display: 'flex',
    justifyContent: 'center',
};

const containerStyle = (backgroundColor) => ({
    border: '1px solid black',
    backgroundColor: backgroundColor,
    padding: '10px',
    flex: 1,
    textAlign: 'center',
    maxWidth: '120px',
    margin: '0 5px',
    borderRadius:'10px',
});

export default MainNavBits;
