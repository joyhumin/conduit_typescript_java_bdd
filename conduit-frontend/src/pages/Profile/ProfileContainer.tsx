import React from "react";
import { Profile } from "./Profile";
import { useParams } from "react-router";

export const ProfileContainer: React.FC = () => {
  const { username } = useParams();
  // const { data } = useProfile("Profile");
  return (<Profile content={username as string} />);
};
