export type InfoResponse = {
  applicationName: string;
  message: string;
};

export const getInfo = async(signal: AbortSignal) : Promise<InfoResponse> => {
    const response = await fetch("/api/info", { signal, headers: { "Accept" : "application/json" } });
    if (!response.ok) {
        throw new Error("API returned an error with status code " + response.status);
    }
    return await response.json();
}
