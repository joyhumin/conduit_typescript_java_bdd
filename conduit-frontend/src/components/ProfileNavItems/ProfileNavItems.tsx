import React from "react";
import { NavMenuItem } from "../NavMenuItem";
import { USERNAME } from "../../hooks";
import { LogOut } from "../LogOut";


export const ProfileNavItems: React.FC = () => {
  console.log("**********ProfileNavItems rendered**********");
  const username = window.sessionStorage.getItem(USERNAME);
  return (
    <React.Fragment>
      <NavMenuItem itemName={`${username}`} to={`/profile/${username}`} />
      <LogOut />
    </React.Fragment>
  );
};
