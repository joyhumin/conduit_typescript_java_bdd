import React from "react";
import { JWT_TOKEN } from "../../hooks";
import { UserHomePage } from "../../components/UserHomePage";
import { GuestHomePage } from "../../components/GuestHomePage";

export const HomePage: React.FC = () => {
  const isLoggedIn = !!window.sessionStorage.getItem(JWT_TOKEN);

  return (
    <React.Fragment>
      {isLoggedIn ? <UserHomePage content={"user home page"} /> : <GuestHomePage />}
    </React.Fragment>
  );
};
