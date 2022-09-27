import React, { useEffect, useState } from "react";
import { User } from "../types";
import { USER } from "../hooks";

type UserProviderProps = { children: React.ReactNode; }

interface UserContext {
  user?: User,
  setUser: (user?: User) => void,
  loading: boolean;
}

const UserStateContext = React.createContext<UserContext | undefined>(undefined);

export function useUserContext() {
  const context = React.useContext(UserStateContext);

  if (context === undefined) {
    throw new Error("user must be defined first");
  }
  return context;
}

export function UserProvider({ children }: UserProviderProps) {
  console.log("**********UserProvider rendered**********");
  const [user, setUser] = useState<User | undefined>(undefined);
  const [loading, setLoading] = useState<boolean>(true);
  const value = { user, setUser, loading } as UserContext; // check storage

  useEffect(() => {
    const currentUser = window.sessionStorage.getItem(USER);
    if (currentUser) {
      console.log("currentUser in context are set");
      // note: automatically log out user if cannot parse the session storage data correctly
      try {
        setUser(JSON.parse(currentUser));
      } catch (e) {
      //  ignore the wrong json format
        sessionStorage.clear();
      }
    }
    setLoading(false);
  }, []); //empty array only run once when render

  return (
    <UserStateContext.Provider value={value}>
      {children}
    </UserStateContext.Provider>
  );
}