import React, { useEffect, useRef } from "react";
import { Box, Container, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import { useAuth, UserSignIn } from "../../hooks";
import { useNavigate } from "react-router";


interface Props {
  content: string;
}

export const SignIn: React.FC<Props> = ({ content }) => {
  console.log(`*********SingIn rendered*********`);
  const [{ currentUser }, signIn] = useAuth();
  const formRef = useRef<HTMLFormElement>(null);
  const navigate = useNavigate();
  // todo: update to use react hook form
  function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    if (formRef.current) {
      const form = new FormData(formRef.current);
      const user = {
        email: form.get("email"),
        password: form.get("password")
      } as UserSignIn;
      signIn(user);
    }
  }

  useEffect(() => {
    if (currentUser) {
      navigate(`/profile/${currentUser.username}`);
    }
  }, [currentUser]);

  return (
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
  );
};
