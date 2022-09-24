import { useCallback, useEffect, useState } from "react";
import { loginRequest, sendNewUserRequest } from "../services";
import { JWT_TOKEN } from "../pages/Registration";
import { User } from "../types";

// export const useAuth = (content: string): UseQueryResult<AuthState, Error> =>
//   useQuery([ "auth", content ], () => retrieveAuth(content), { retry: false });

export interface UserSignIn {
  email: string;
  password: string;
}

export interface UserRegistration extends UserSignIn {
  username: string;
}

type SignHandler = (user: UserSignIn) => void
type RegistrationHandler = (user: UserRegistration) => void

type UseAuthResult = [{ currentUser?: User }, SignHandler, RegistrationHandler];

export const useAuth = (): UseAuthResult => {
  const [currentUser, setCurrentUser] = useState<User>();

  // note: whenever the components which call `useAuth` get re-rendered,
  // useAuth will get called again, thus, the handlers will be defined again (different object references).

  const signInHandler = useCallback((user: UserSignIn) => {
    const loginReq = { user };

    loginRequest(loginReq).then(
      data => setCurrentUser(data.user)
    );
  }, [loginRequest]);


  const registrationHandler = useCallback((newUser: UserRegistration) => {
    const registrationReq = { user: newUser };
    sendNewUserRequest(registrationReq).then(
      data => {
        setCurrentUser(data.user);
      }
    );
  }, [sendNewUserRequest]);


// todo: refactor same code as in registration
  useEffect(() => {
    if (currentUser && currentUser.token) {
      window.sessionStorage.setItem(JWT_TOKEN, currentUser.token);
    }
  }, [currentUser]);

  return [{ currentUser }, signInHandler, registrationHandler];
  // note: usually put handlers in the array,
  // rather than inside of object to prevent unnecessary rendering
};