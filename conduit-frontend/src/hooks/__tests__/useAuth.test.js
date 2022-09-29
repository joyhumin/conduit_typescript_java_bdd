import { retrieveAuth } from "../../services";
import { useAuth } from "../useAuth";
import { useQuery } from "react-query";

jest.mock("react-query");
jest.mock("../../services");

describe("AuthHooks", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("use Auth content", () => {
    const content = chance.string();
    const expected = chance.object();

    // Given
    useQuery.mockImplementationOnce((_, fn) => fn());
    retrieveAuth.mockReturnValueOnce(expected);

    // When
    const actual = useAuth(content);

    // Then
    expect(useQuery.mock.calls[0][0]).toEqual([ "auth", content ]);
    expect(useQuery.mock.calls[0][2]).toEqual({ retry: false });
    expect(retrieveAuth).toBeCalledWith(content);
    expect(actual).toBe(expected);
  });
});
