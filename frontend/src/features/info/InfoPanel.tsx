import React, { useEffect } from 'react'
import type { InfoResponse } from './infoApi';
import { getInfo } from './infoApi';

export const InfoPanel = () => {
    const [info, setInfo] = React.useState<InfoResponse | null>(null);
    const [loading, setLoading] = React.useState<boolean>(true);
    const [error, setError] = React.useState<string | null>(null);

    useEffect(() => {
        const controller = new AbortController();
        const signal = controller.signal;

        const fetchInfo = async () => {
            try {
                const result = await getInfo(signal);

                if (signal.aborted) {
                    console.log('Fetch aborted');
                    return;
                }
                setInfo(result);
                setError(null);
            } catch (err) {
                if (signal.aborted) {
                    console.log('Fetch aborted');
                    return;
                }
                setError('Could not load application information.');
                console.error(err);
            } finally {
                if (!signal.aborted) setLoading(false);
            }
        }

        fetchInfo();

        return () => {
            controller.abort();
        };
    }, []);

    if (loading) return <div>Loading application information…</div>;

    if (error) return <div>Could not load application information. Please try again.</div>;

  return (
    <div>
      {info && (
        <div>
          <h2>Application Information</h2>
          <p>Name: {info.applicationName}</p>
          <p>Message: {info.message}</p>
        </div>
      )}
    </div>
  )
}
