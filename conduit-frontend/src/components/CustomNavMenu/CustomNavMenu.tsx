import React from "react";
import { ProfileNavItems } from "../ProfileNavItems";
import { GuestNavItems } from "../GuestNavItems";
import { useUserContext } from "../../contexts";


export const CustomNavMenu: React.FC = () => {
  console.log("**********CustomNavMenu rendered**********");
  // note: The updates to context values doesn't trigger re-render for
  // all the children of the provider, rather only components that are rendered from within the Consumer,
  const { user} = useUserContext();
  console.log("user from context", user);


  return <React.Fragment>
    {user ? <ProfileNavItems /> : <GuestNavItems />}
  </React.Fragment>;
};
