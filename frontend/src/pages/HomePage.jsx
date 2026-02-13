import { useEffect, useState } from "react";
import ShopList from "../components/shop/ShopList.jsx";
import shopApi from "../api/shopApi";

export default function HomePage() {
    const [shops, setShops] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function loadShops() {
            try {
                const res = await shopApi.getAllShops();
                setShops(res.data);
            } catch (err) {
                console.error("Failed to load shops:", err);
            } finally {
                setLoading(false);
            }
        }

        loadShops();
    }, []);

    if (loading) return <div>Loading shops...</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold mb-6">Shops</h1>
            <ShopList shops={shops} />
        </div>
    );
}
