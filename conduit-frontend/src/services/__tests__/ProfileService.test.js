import fetchMock from "fetch-mock";
import { retrieveProfile } from "../ProfileService";

describe("ProfileService", () => {
  afterEach(() => {
    fetchMock.restore();
  });

  test("Can retrieve Profile state", async () => {
    const content = chance.string();
    const expected = chance.object();

    // Given
    fetchMock.mock(`/assets/data/${content}.json`, JSON.stringify(expected));

    // When
    const actual = await retrieveProfile(content);

    // Then
    expect(actual).toEqual(expected);
  });

  test("Can fail to retrieve Profile state", async () => {
    const message = chance.string();

    // Given
    fetchMock.mock("*", { status: 400, body: { error: message } });

    // When
    const actual = await catchAsyncError(() => retrieveProfile(chance.string()));

    // Then
    expect(actual.message).toBe(message);
  });

  test("Can fail to fetch Profile state", async () => {
    const message = chance.string();

    // Given
    fetchMock.mock("*", { throws: new Error(message) });

    // When
    const actual = await catchAsyncError(() => retrieveProfile(chance.string()));

    // Then
    expect(actual.message).toBe(message);
  });
});
