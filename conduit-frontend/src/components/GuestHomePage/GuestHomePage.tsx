import React from "react";
import { Banner } from "../Banner";


export const GuestHomePage: React.FC = () => {
  console.log("**********GuestHomePage rendered**********");

  return <Banner
    headline={"conduit"}
    subheadline={"A place to share your knowledge."} />;
};
