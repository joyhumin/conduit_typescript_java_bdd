import React, { useEffect, useRef, useState } from "react";
import { Box, Container, Typography } from "@mui/material";
import { Link as RouterLink, Navigate } from "react-router-dom";
import { User } from "../../types";
import { loginRequest } from "../../services";
import { JWT_TOKEN } from "../Registration";


interface Props {
  content: string;
}

export const SignIn: React.FC<Props> = ({ content }) => {
  console.log(`*********SingIn rendered*********`);
  const [currentUser, setCurrentUser] = useState<User>();
  const formRef = useRef<HTMLFormElement>(null);

  function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    if (formRef.current) {
      const form = new FormData(formRef.current);
      const loginReq = {
        user: {
          email: form.get("email"),
          password: form.get("password")
        }
      };

      loginRequest(loginReq).then(
        data => setCurrentUser(data.user)
      );
    }
  }

  // todo: refactor same code as in registration
  useEffect(() => {
    console.log("Sign In side effect kick off");
    if (currentUser && currentUser.token) {
      window.sessionStorage.setItem(JWT_TOKEN, currentUser.token);
    }
  }, [currentUser]);

  return (
    <React.Fragment>
      {currentUser && (
        <Navigate to={`/profile/${currentUser.username}`} replace={true}/>
      )}
      <Container maxWidth={"sm"}>
        <Box
          display={"flex"}
          flexDirection={"column"}
          gap={"10px"}
          textAlign={"center"}
          paddingX={"15px"}>
          <Typography variant="h3">{content}</Typography>
          <Box component={RouterLink}
               to={"/register"}
               sx={{ textDecoration: "none", color: "text.secondary" }}>
            Need an account?
          </Box>

          <form ref={formRef} onSubmit={handleSubmit}>
            <p>
              <label htmlFor="email">Email: </label>
              <input type="email" id="email" name="email" required={true} />
            </p>
            <p>
              <label htmlFor="pwd">Password: </label>
              <input type="password" id="pwd" name="password" required={true} />
            </p>
            <button type={"submit"}>Sign in</button>
          </form>
        </Box>
      </Container>
    </React.Fragment>
  );
};
