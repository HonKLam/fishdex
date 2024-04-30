
import React from 'react';
// Import CSS file for styling

const ErrorPage = () => {
    return (
      <div className="error-container">
          <h1>404</h1>
          <h2>Seite nicht gefunden</h2>
          <p>Verzeihung, die Seite, die Sie suchen existiert, nicht</p>
          <p>Klicken Sie <a href="/">hier,</a> um auf die Startseite zurückzukehren.</p>
      </div>
    );
};

export default ErrorPage;
