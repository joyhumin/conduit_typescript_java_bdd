import React from "react";
import { Profile } from "./Profile";
import { useProfile } from "../../hooks";
import { useParams } from "react-router";

export const ProfileContainer: React.FC = () => {
  const { username } = useParams();
  const { data } = useProfile("Profile");
  console.log(data);
  return (<Profile content={ username as string } />);
};
