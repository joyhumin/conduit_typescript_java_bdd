import { useQuery, UseQueryResult } from "react-query";
import { retrieveProfile } from "../services";
import { ProfileState } from "../types";

export const useProfile = (content: string): UseQueryResult<ProfileState, Error> =>
  useQuery([ "profile", content ], () => retrieveProfile(content), { retry: false });
