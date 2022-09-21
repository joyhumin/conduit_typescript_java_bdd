import { retrieveProfile } from "../../services";
import { useProfile } from "../useProfile";
import { useQuery } from "react-query";

jest.mock("react-query");
jest.mock("../../services");

describe("ProfileHooks", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("use Profile content", () => {
    const content = chance.string();
    const expected = chance.object();

    // Given
    useQuery.mockImplementationOnce((_, fn) => fn());
    retrieveProfile.mockReturnValueOnce(expected);

    // When
    const actual = useProfile(content);

    // Then
    expect(useQuery.mock.calls[0][0]).toEqual([ "profile", content ]);
    expect(useQuery.mock.calls[0][2]).toEqual({ retry: false });
    expect(retrieveProfile).toBeCalledWith(content);
    expect(actual).toBe(expected);
  });
});
