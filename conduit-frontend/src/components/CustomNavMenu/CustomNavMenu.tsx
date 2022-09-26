import React from "react";
import { ProfileNavItems } from "../ProfileNavItems";
import { GuestNavItems } from "../GuestNavItems";
import { useUserContext } from "../../contexts";
import { JWT_TOKEN } from "../../hooks";
import { EMPTY_USER } from "../../types";


export const CustomNavMenu: React.FC = () => {
  console.log("**********CustomNavMenu rendered**********");
  const { user } = useUserContext();
  const isLoggedIn = !!window.sessionStorage.getItem(JWT_TOKEN);
  const userLoggedIn = user !== EMPTY_USER && isLoggedIn;

  return <React.Fragment>
    {userLoggedIn ? <ProfileNavItems /> : <GuestNavItems />}
  </React.Fragment>;
};
