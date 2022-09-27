import React from "react";
import { NavMenuItem } from "../NavMenuItem";
import { LogOut } from "../LogOut";
import { useUserContext } from "../../contexts";


export const ProfileNavItems: React.FC = () => {
  console.log("**********ProfileNavItems rendered**********");
  const { user } = useUserContext();
  return (
    <React.Fragment>
      <NavMenuItem itemName={`${user?.username}`} to={`/profile/${user?.username}`} ariaLabel={"user-profile-name"} />
      <LogOut />
    </React.Fragment>
  );
};
