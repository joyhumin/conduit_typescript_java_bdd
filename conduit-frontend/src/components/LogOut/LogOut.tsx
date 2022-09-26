import React from "react";
import { Button } from "@mui/material";
import { Logout } from "@mui/icons-material";
import { useNavigate } from "react-router";
import { useUserContext } from "../../contexts";
import { EMPTY_USER } from "../../types";


export const LogOut: React.FC = () => {
  const navigate = useNavigate();
  const { setUser } = useUserContext();

  function handleClick() {
    setUser(EMPTY_USER);
    sessionStorage.clear();
    navigate("/login");
  }

  return (
    <Button
      variant={"contained"}
      size={"small"}
      endIcon={<Logout />}
      sx={{
        color: "white",
        fontWeight: "bold",
      }}
      onClick={handleClick}
    >
      Log out
    </Button>
  );
};
