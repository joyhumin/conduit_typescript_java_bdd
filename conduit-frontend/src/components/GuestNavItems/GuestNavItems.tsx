import React from "react";
import { NavMenuItem } from "../NavMenuItem";


export const GuestNavItems: React.FC = () => {
  return <React.Fragment>
    <NavMenuItem itemName={"Sign in"} to={"login"} />
    <NavMenuItem itemName={"Sign up"} to={"register"} />
  </React.Fragment>;
};
