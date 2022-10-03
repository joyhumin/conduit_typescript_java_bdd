import { useCallback, useEffect, useState } from "react";
import { loginRequest, sendNewUserRequest } from "../services";
import { User } from "../types";
import { useUserContext } from "../contexts";

// export const useAuth = (content: string): UseQueryResult<AuthState, Error> =>
//   useQuery([ "auth", content ], () => retrieveAuth(content), { retry: false });

export const USER = "user";

export interface UserSignIn {
  email: string;
  password: string;
}

export interface UserRegistration extends UserSignIn {
  username: string;
}

type SignHandler = (user: UserSignIn) => void
type RegistrationHandler = (user: UserRegistration) => void

type UseAuthResult = [
  { currentUser?: User, isLoggedIn?: boolean },
  SignHandler,
  RegistrationHandler];

export const useAuth = (): UseAuthResult => {
  const [currentUser, setCurrentUser] = useState<User>();
  const { setUser } = useUserContext();

  // note: whenever the components which call `useAuth` get re-rendered,
  // useAuth will get called again, thus, the handlers will be defined again (different object references).

  const signInHandler = useCallback((signReq: UserSignIn) => {
    const loginReq = { user: signReq };

    loginRequest(loginReq).then(
      data => {
        setCurrentUser(data.user);
      }
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
    if (currentUser) {
      window.sessionStorage.setItem(USER, JSON.stringify(currentUser));
      console.log("useEffect in useAuth called, and set context user to ", currentUser);
      setUser(currentUser);
    }
  }, [currentUser, setUser]);

  return [{ currentUser }, signInHandler, registrationHandler];
  // note: usually put handlers in the array,
  // rather than inside of object to prevent unnecessary rendering
};