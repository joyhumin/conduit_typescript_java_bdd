import React from "react";
import { UserHomePage } from "../../components/UserHomePage";
import { GuestHomePage } from "../../components/GuestHomePage";
import { useUserContext } from "../../contexts";

export const HomePage: React.FC = () => {
  const { user } = useUserContext();

  return (
    <React.Fragment>
      {user ? <UserHomePage content={"user home page"} /> : <GuestHomePage />}
    </React.Fragment>
  );
};
