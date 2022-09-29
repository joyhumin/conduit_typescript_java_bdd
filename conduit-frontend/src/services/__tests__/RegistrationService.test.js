import fetchMock from "fetch-mock";
import { sendNewUserRequest } from "../AuthenticationService";

describe("RegistrationService", () => {
  afterEach(() => {
    fetchMock.restore();
  });

  test("Can retrieve Registration state", async () => {
    const content = chance.string();
    const expected = chance.object();

    // Given
    fetchMock.mock(`/assets/data/${content}.json`, JSON.stringify(expected));

    // When
    const actual = await sendNewUserRequest(content);

    // Then
    expect(actual).toEqual(expected);
  });

  test("Can fail to retrieve Registration state", async () => {
    const message = chance.string();

    // Given
    fetchMock.mock("*", { status: 400, body: { error: message } });

    // When
    const actual = await catchAsyncError(() => sendNewUserRequest(chance.string()));

    // Then
    expect(actual.message).toBe(message);
  });

  test("Can fail to fetch Registration state", async () => {
    const message = chance.string();

    // Given
    fetchMock.mock("*", { throws: new Error(message) });

    // When
    const actual = await catchAsyncError(() => sendNewUserRequest(chance.string()));

    // Then
    expect(actual.message).toBe(message);
  });
});
